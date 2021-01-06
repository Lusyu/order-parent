package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 对指定demo新增嵌套数据
 */
Contract.make {
    request {
        method PATCH()
        url $(c(regex("/demo/id/[0-9a-z]{24}/saveChildren/"))
                , p("/demo/id/5ff3e1ab3017c813400bf90e/saveChildren/"))
        body([[
                      embedId: $(c(regex("[0-9a-z]{24}")), p("35bbb4562d49c8016ea35bb6")),
                      name   : $(c(regex(".+")), p("机械革命X9Ti")),
                      price  : $(c(regex(".+")), p("1000")),
              ]])
        headers {
            header('Authorization',
                    $(c(regex('Bearer [0-9a-z]{8}(-[0-9a-z]{4}){3}-[0-9a-z]{12}'))
                            , p('Bearer 4e57dbe7-6a58-401b-9017-037909799e8a')))
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status OK()
        body(
                success: $(c(true), p(regex(anyBoolean())))
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
