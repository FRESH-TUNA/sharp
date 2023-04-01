package com.freshtuna.sharp.util


import com.freshtuna.sharp.page.SharpPageRequest
import com.freshtuna.sharp.page.SharpSort
import com.freshtuna.sharp.page.SharpSortDirection
import com.freshtuna.sharp.page.SharpSortOrder
import org.springframework.data.domain.Pageable

class SpringPageableConverter {

    companion object {

        fun convert(pageable: Pageable): SharpPageRequest {

            val sharpSort = SharpSort()

            pageable.sort.stream().forEach {
                order -> sharpSort.orders.add(SharpSortOrder(
                    order.property,
                    if (order.isAscending) SharpSortDirection.ASC else SharpSortDirection.DESC
                ))
            }

            return SharpPageRequest(pageable.pageNumber.toLong(), pageable.pageSize.toLong(), sharpSort)
        }


//        fun convert(sharpPage: SharpPageRequest)
//                = PageRequest.of(sharpPage.pageNumber.toInt(), sharpPage.pageSize.toInt(), sort(sharpPage.sharpSort))
//
//        private fun sort(sharpSort: SharpSort)
//                = Sort.by(sharpSort.orders.map {
//                order -> Sort.Order(Sort.Direction.valueOf(order.direction.name), order.by)
//        })
    }
}
