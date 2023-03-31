package com.freshtuna.sharp.api.response;

open class HalCollectionResponse: HalAbstractResponse() {

    private val _embedded: HashMap<String, Any> = HashMap()

    fun addCollection(key: String, collection: Collection<Any>) {
        _embedded[key] = collection;
    }
}
