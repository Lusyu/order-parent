package contracts.controller.OrderController

/**
 * 对指定demo删除嵌套数据
 */
import org.springframework.cloud.contract.spec.Contract

/**
 * 对指定demo删除嵌套数据
 */
Contract.make {
    request {
        method PATCH()
        url $(c(regex("/demo/id/[0-9a-z]{24}/deleteChildren/"))
                ,p("/demo/id/5ff3e1ab3017c813400bf90e/deleteChildren/"))
        body([
                "35bbb4562d49c8016ea35bb7",
                "35bbb4562d49c8016ea35bb6",
        ])
        headers {
            header("Authorization", $(c(regex("Bearer [0-9a-z]{8}(-[0-9a-z]{4}){3}-[0-9a-z]{12}"))
                    , p("Bearer 4e57dbe7-6a58-401b-9017-037909799e8a")))
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status OK()
        body(
                success:$(c(true),p(anyBoolean()))
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
