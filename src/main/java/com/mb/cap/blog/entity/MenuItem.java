package com.mb.cap.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "menu_item", indexes = {
        @Index(name = "menu_item_idx", columnList = "title, path")
})
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem extends BaseEntity {
    @Id
    private Long id;
    private String title;
    private String code;
    private String path;
    private String icon;
    private Long parentId;
    private Integer orderId;
    @Column(columnDefinition = "text")
    private String description;
    private Boolean isDirectory;
}
