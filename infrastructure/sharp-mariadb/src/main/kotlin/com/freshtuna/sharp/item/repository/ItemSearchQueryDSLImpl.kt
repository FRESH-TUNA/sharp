package com.freshtuna.sharp.item.repository

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.item.command.SearchItemCommand
import com.freshtuna.sharp.item.entity.ItemEntity
import com.freshtuna.sharp.item.entity.QItemEntity
import com.freshtuna.sharp.page.SharpPage
import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.page.SharpSort
import com.freshtuna.sharp.page.SharpSortDirection

import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.core.types.dsl.Wildcard
import com.querydsl.jpa.impl.JPAQueryFactory

import org.springframework.stereotype.Component

@Component
class ItemSearchDSLImpl(
    private val queryFactory: JPAQueryFactory
) : ItemSearchRepository {

    private val item = QItemEntity.itemEntity

    override fun search(commend: SearchItemCommand, sellerId: SharpID): SharpPage<ItemEntity> {
        val wherePredicate = generateSearchItemPredicate(commend)

        val count = queryFactory
            .select(Wildcard.count)
            .from(item)
            .where(item.seller.id.eq(sellerId.longId()))
            .where(wherePredicate)
            .fetchOne()

        val data = queryFactory
            .select(item)
            .from(item)
            .where(item.seller.id.eq(sellerId.longId()))
            .where(wherePredicate)
            .orderBy(*orderBys(commend.pageRequest.sharpSort).toTypedArray())
            .offset(offset(commend.pageRequest))
            .limit(limit(commend.pageRequest))
            .fetch()

        return SharpPage(data, count!!, commend.pageRequest)
    }


    override fun findById(id: SharpID): ItemEntity {
        return queryFactory
            .select(item)
            .from(item)
            .innerJoin(item.sku).fetchJoin()
            .leftJoin(item.policies).fetchJoin()
            .where(item.id.eq(id.toString().toLong()))
            .fetchOne()!!
    }


    /**
     * helpers
     */
    private fun generateSearchItemPredicate(command: SearchItemCommand): Predicate {
        if(command.query.isBlank())
            return Expressions.asBoolean(true).isTrue

        return Expressions.asBoolean(true).isFalse
                .or(item.name.startsWith(command.query))
    }

    /**
     * https://velog.io/@seungho1216/Querydsl%EB%8F%99%EC%A0%81-sorting%EC%9D%84-%EC%9C%84%ED%95%9C-OrderSpecifier-%ED%81%B4%EB%9E%98%EC%8A%A4-%EA%B5%AC%ED%98%84
     */
    private fun orderBys(sort: SharpSort): List<OrderSpecifier<*>> {

        return sort.orders.stream().map {
            val direction = if (it.direction == SharpSortDirection.ASC) Order.ASC else Order.DESC
            val expression = PathBuilder(item.type, item.metadata).get(it.by)
                    as Expression<out Comparable<ItemEntity>>
            OrderSpecifier(direction, expression)
        }.toList()
    }

    private fun offset(page: SharpPageRequest) = page.pageNumber*page.pageSize

    private fun limit(page: SharpPageRequest) = page.pageSize
}
