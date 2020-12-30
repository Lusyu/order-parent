package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 修改指定demo的地址
 */
Contract.make {
    request {
        method PATCH()
        urlPath ($(c(regex("/demo/[0-9a-z]{24}/address/"))
                , p("/demo/59bbb4290d49c8016ea18bb6/address/"))){
            queryParameters {
                parameter("address",$(c(regex(".+")),p("广州市...")))
            }
        }
        headers {
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status OK()
        body(
                success: $(c(true), producer(regex(anyBoolean())))
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
