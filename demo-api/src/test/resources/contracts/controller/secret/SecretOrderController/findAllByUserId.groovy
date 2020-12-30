package contracts.controller.secret.SecretOrderController

import org.springframework.cloud.contract.spec.Contract


/**
 * 根据用户id获取订单
 */
Contract.make {
    request {
        method GET()
        url $(c(regex("/secret/demo/[0-9a-z]{24}/demos/")), p("/secret/demo/59bbb4290d49c8016ea18bb4/demos/"));
    }
    response {
        status OK()
        body(
                success: $(c(true), p(regex(anyBoolean()))),
                data: [[
                               orderId       : $(c("59bbb4290d49c8016ea18bb6"), p(regex("[0-9a-z]{24}"))),
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
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
