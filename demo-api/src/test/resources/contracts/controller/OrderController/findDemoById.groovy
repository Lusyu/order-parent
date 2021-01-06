package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 根据id获取指定demo的详情
 */
Contract.make {
    request {
        method GET()
        url $(c(regex("/demo/[0-9a-z]{24}/")), p("/demo/5ff3e1ab3017c813400bf90e/"))
        headers {
            header('Authorization', $(consumer(regex('Bearer [0-9a-z]{8}(-[0-9a-z]{4}){3}-[0-9a-z]{12}'))
                    , producer('Bearer 7879755a-a90f-11e9-8547-0f8730a4fe76')))
        }
    }
    response {
        status OK()
        body(
                success: $(c(true), p(regex(anyBoolean()))),
                data: [
                        id        : $(c("5ff3e1ab3017c813400bf90e"), p(regex("[0-9a-z]{24}"))),
                        name      : $(c("jack"), p(regex(".+"))),
                        price     : $(c("1000"), p(regex(".+"))),
                        createTime: $(c("2020-10-01"), p(regex(".+"))),
                        children  : [[
                                             id   : $(c("35bbb4562d49c8016ea35bb6"), p(regex(".+"))),
                                             name : $(c("机械革命X10Ti"), p(regex(".+"))),
                                             price: $(c("1000"), p(regex(".+")))
                                     ]]
                ]
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
