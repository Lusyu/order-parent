package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 修改指定用户的订单
 */
Contract.make {
    request {
        method PATCH()
        url $(c(regex("/order/[0-9a-z]{24}/")), p("/order/59bbb4290d49c8016ea18bb6/"))
        body(
                [
                        orderId       : $(c(regex("[0-9a-z]{24}")), p("59bbb4290d49c8016ea18bb6")),
                        orderNumber   : $(c(regex("[0-9]{24}")), p("594784290949780165218436")),
                        userId        : $(c(regex("[0-9a-z]{24}")), p("59bbb4290d49c8016ea18bb4")),
                        payPrice      : $(c(regex(".+")), p("999")),
                        shipTime      : $(c(regex(".+")), p("2020-10-01")),
                        receiptAddress: $(c(regex(".+")), p("广州市....")),
                        status        : $(c(regex("[0-3]{1}")), p("1")),
                        list          : [[
                                                 id   : $(c(regex(".+")), p("35bbb4562d49c8016ea35bb6")),
                                                 name : $(c(regex(".+")), p("jack")),
                                                 price: $(c(regex(".+")), p("10"))
                                         ]]
                ]
        )
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
