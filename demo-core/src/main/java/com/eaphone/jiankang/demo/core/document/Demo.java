package com.eaphone.jiankang.demo.core.document;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedDemo;
import com.eaphone.smarthealth.jsonview.View;
import com.eaphone.smarthealth.model.BaseDocument;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * demo实体
 */
@Data
@Document(collection = "pms_demo")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@CompoundIndex(name = "idx_demo_id", def = "{demoId:-1}")
public class Demo extends BaseDocument implements Serializable {

    public static final String DEMO_ID = "demoId";
    public static final String USER_ID="userId";
    public static final String STATUS = "status";
    public static final String RECEIPT_ADDRESS="receiptAddress";


    @Indexed
    @JsonView(View.class)
    private String demoId;
    /**
     * 用户id
     */
    @Indexed
    @JsonView(View.class)
    private String userId;
    /**
     * 总价
     */
    @JsonView(View.class)
    private Float payPrice;
    /**
     * 付款时间
     */
    @JsonView(View.class)
    private Date shipTime;
    /**
     * 收货地址
     */
    @JsonView(View.class)
    private String receiptAddress;
    /**
     * 状态
     */
    @JsonView(View.class)
    private Integer status;
    /**
     * 嵌套demo
     */
    @JsonView(View.class)
    private List<EmbeddedDemo> list;
}
