package com.freshtuna.sharp.inventory.command

import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.price.Price
import com.freshtuna.sharp.spec.Spec
import java.time.LocalDateTime

class SkuCommand(
    val skuId: SharpID,
    val barcode: String,
    val description: String = "",

    val price: Price,
    val spec: Spec,

    val expireDate: LocalDateTime,
    val manufactureDate: LocalDateTime
) {

//    init {
//        validate()
//    }
//
//    private fun validate() {
//        val msgMap = HashMap<String, String>()
//
//        if(name.isNullOrEmpty())
//            msgMap["name"] = "이름을 입력해주세요"
//        if(barcode.isNullOrEmpty())
//            msgMap["barcode"] = "바코드를 입력해주세요"
//
//        if (msgMap.isNotEmpty())
//            Oh.formValidationError(msgMap)
//    }
}
