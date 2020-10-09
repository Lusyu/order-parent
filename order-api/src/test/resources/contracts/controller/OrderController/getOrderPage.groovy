package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 订单分页
 */
Contract.make {
    request {
        method GET()
        urlPath("/order/page/") {
            queryParameters {
                parameter("currentPage", $(c(regex("[0-9]+")), p("1")))
                parameter("pageSize", $(c(regex("[0-9]+")), p("2")))
                parameter("minPayPrice", $(c(regex("[0-9]+")), p("100")))
                parameter("maxPayPrice", $(c(regex("[0-9]+")), p("1000")))
                parameter("minShipTime", $(c(regex(".+")), p("2020-09-03 10:22:33")))
                parameter("maxShipTime", $(c(regex(".+")), p("2020-10-19 10:22:33")))
                parameter("orderNumber", $(c(regex(".+")), p("59478429094978016521843")))
                parameter("status", $(c(regex("[0-3]{1}")), p("1")))
            }
        }
    }
    response {
        status OK()
        body(
                success: $(c(true), p(regex(anyBoolean()))),
                data: [
                        currentPage: $(c("2"), p(regex("[0-9]+"))),
                        pageSize   : $(c("4"), p(regex("[0-9]+"))),
                        totalCount : $(c("100"), p(regex("[0-9]+"))),
                        totalPage  : $(c("25"), p(regex("[0-9]+"))),
                        orders     : [[
                                              orderId       : $(c("59bbb4290d49c8016ea18bb6"), p(regex("[0-9a-z]{24}"))),
                                              orderNumber   : $(c("594784290949780165218436"), p(regex("[0-9]{24}"))),
                                              userId        : $(c("59bbb4290d49c8016ea18bb4"), p(regex("[0-9a-z]{24}"))),
                                              payPrice      : $(c("999"), p(regex(".+"))),
                                              shipTime      : $(c("2020-10-01"), p(regex(".+"))),
                                              receiptAddress: $(c("广州市...."), p(regex(".+"))),
                                              status        : $(c("1"), p(regex("[0-3]{1}"))),
                                              list          : [[
                                                                       id   : $(c("35bbb4562d49c8016ea35bb6"), p(regex(".+"))),
                                                                       name : $(c("jack"), p(regex(".+"))),
                                                                       price: $(c("10"), p(regex(".+")))

                                                               ]]
                                      ]]
                ]
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
