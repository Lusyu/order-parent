package contracts.controller.secret.SecretOrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 获取指定demo详情
 */
Contract.make {
    request {
        method GET()
        url $(c(regex("/secret/demo/[0-9a-z]{24}/")), p("/secret/demo/5ff3e1ab3017c813400bf90e/"))
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
