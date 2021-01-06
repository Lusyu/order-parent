package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 更新指定嵌套的embedDemo
 */
Contract.make {
    request {
        method PATCH()
        url $(c(regex("/demo/id/[0-9a-z]{24}/updateChildren/"))
                , p("/demo/id/5ff3e1ab3017c813400bf90e/updateChildren/"))
        body(
                embedId: $(c(regex("[0-9a-z]{24}")), p("a9bbb4290d49c8016ea18b21")),
                name: $(c(regex(".+")), p("机械革命X10Ti")),
                price: $(c(regex(".+")), p("10000"))

        )
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
