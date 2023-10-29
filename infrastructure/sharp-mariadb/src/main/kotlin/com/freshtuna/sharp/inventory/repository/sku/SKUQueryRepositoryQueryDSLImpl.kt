package com.freshtuna.sharp.inventory.repository.sku

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.inventory.command.sku.SearchSkuCommand
import com.freshtuna.sharp.inventory.dto.SkuInventoriesDto

import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.entity.QMariaDBInventory
import com.freshtuna.sharp.inventory.entity.QMariaDBSKU

import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.page.SharpSort
import com.freshtuna.sharp.page.SharpSortDirection
import com.querydsl.core.types.*

import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.core.types.dsl.Wildcard

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class SKUQueryRepositoryQueryDSLImpl(
    private val queryFactory: JPAQueryFactory
) : SKUQueryRepository {

    private val sku = QMariaDBSKU.mariaDBSKU
    private val inventory = QMariaDBInventory.mariaDBInventory

    override fun search(commend: SearchSkuCommand, sellerId: SharpID): SharpPage<MariaDBSKU> {
        val wherePredicate = generateSearchSkuPredicate(commend)

        val count = queryFactory
            .select(Wildcard.count)
            .from(sku)
            .where(sku.seller.id.eq(sellerId.longId()))
            .where(wherePredicate)
            .fetchOne()

        val data = queryFactory
            .select(sku)
            .from(sku)
            .where(sku.seller.id.eq(sellerId.longId()))
            .where(wherePredicate)
            .orderBy(*orderBys(commend.sharpPageRequest.sharpSort).toTypedArray())
            .offset(offset(commend.sharpPageRequest))
            .limit(limit(commend.sharpPageRequest))
            .fetch()

        return SharpPage(data, count!!, commend.sharpPageRequest)
    }

    override fun skuWithInventories(skus: List<MariaDBSKU>): List<SkuInventoriesDto> {

        val result = skus.stream().map { sku -> SkuInventoriesDto(sku) }.toList()

        val inventories = queryFactory
            .select(inventory)
            .from(inventory)
            .where(inventory.sku.`in`(skus))
            .fetch()

        for(inventory in inventories) {
            for(dto in result) {
                if(dto.sku == inventory.sku) {
                    dto.inventories.add(inventory)
                    break
                }
            }
        }

        return result
    }

    override fun findById(id: SharpID): MariaDBSKU {

        return queryFactory
            .select(sku)
            .from(sku)
            .where(sku.id.eq(id.longId()))
            .fetchOne()!!
    }


    /**
     * helpers
     */
    private fun generateSearchSkuPredicate(command: SearchSkuCommand): Predicate {
        if(command.query.isBlank())
            return Expressions.asBoolean(true).isTrue

        return Expressions.asBoolean(true).isFalse
                .or(sku.name.startsWith(command.query))
                .or(sku.barcode.startsWith(command.query))
    }

    /**
     * https://velog.io/@seungho1216/Querydsl%EB%8F%99%EC%A0%81-sorting%EC%9D%84-%EC%9C%84%ED%95%9C-OrderSpecifier-%ED%81%B4%EB%9E%98%EC%8A%A4-%EA%B5%AC%ED%98%84
     */
    private fun orderBys(sort: SharpSort): List<OrderSpecifier<*>> {

        return sort.orders.stream().map {
            val direction = if (it.direction == SharpSortDirection.ASC) Order.ASC else Order.DESC
            val expression = PathBuilder(sku.type, sku.metadata).get(it.by)
                    as Expression<out Comparable<MariaDBSKU>>
            OrderSpecifier(direction, expression)
        }.toList()
    }

    private fun offset(page: SharpPageRequest) = page.pageNumber*page.pageSize

    private fun limit(page: SharpPageRequest) = page.pageSize
}
