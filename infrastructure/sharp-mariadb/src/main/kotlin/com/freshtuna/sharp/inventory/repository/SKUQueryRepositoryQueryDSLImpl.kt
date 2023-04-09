package com.freshtuna.sharp.inventory.repository

import com.freshtuna.sharp.id.PublicId
import com.freshtuna.sharp.inventory.command.SearchSkuCommand

import com.freshtuna.sharp.inventory.dto.SkuWithStocksDTO
import com.freshtuna.sharp.inventory.entity.MariaDBSKU
import com.freshtuna.sharp.inventory.entity.MariaDBStock
import com.freshtuna.sharp.inventory.entity.QMariaDBSKU
import com.freshtuna.sharp.inventory.entity.QMariaDBStock

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
import java.util.*
import kotlin.streams.toList

@Component
class SKUQueryRepositoryQueryDSLImpl(
    private val queryFactory: JPAQueryFactory
) : SKUQueryRepository {

    private val sku = QMariaDBSKU.mariaDBSKU
    private val stock = QMariaDBStock.mariaDBStock

    override fun search(commend: SearchSkuCommand, sellerId: PublicId): SharpPage<MariaDBSKU> {
        val wherePredicate = generateSearchSkuPredicate(commend)

        val count = queryFactory
            .select(Wildcard.count)
            .from(sku)
            .where(sku.sellerId.eq(UUID.fromString(sellerId.toString())))
            .where(wherePredicate)
            .fetchOne()

        val data = queryFactory
            .select(sku)
            .from(sku)
            .where(sku.sellerId.eq(UUID.fromString(sellerId.toString())))
            .where(wherePredicate)
            .orderBy(*orderBys(commend.sharpPageRequest.sharpSort).toTypedArray())
            .offset(offset(commend.sharpPageRequest))
            .limit(limit(commend.sharpPageRequest))
            .fetch()

        return SharpPage(data, count!!, commend.sharpPageRequest)
    }

    override fun skuWithStocks(skus: List<MariaDBSKU>): List<SkuWithStocksDTO> {

        val result = skus.stream().map { sku -> SkuWithStocksDTO(sku) }.toList()

        val stocks = queryFactory
            .select(stock)
            .from(stock)
            .where(stock.sku.`in`(skus))
            .fetch()

        for(stock in stocks) {
            for(dto in result) {
                if(dto.sku == stock.sku) {
                    dto.stocks.add(stock)
                    break
                }
            }
        }

        return result
    }

    override fun findById(id: PublicId): MariaDBSKU {
        return queryFactory
            .select(sku)
            .from(sku)
            .where(sku.id.eq(id.toString().toLong()))
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
