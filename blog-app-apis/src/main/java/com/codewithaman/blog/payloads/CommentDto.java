package com.codewithaman.blog.payloads;

import com.codewithaman.blog.entities.Post;
import com.codewithaman.blog.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private int id;
    private String content;



}
