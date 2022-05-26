package com.codewithaman.blog.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PostResponse {
	private List<PostDto> content;
	private int pageNumber;
	private  int pageSize;
	private long totalElement;
	private int totalPage;
	private boolean lastPage;
	
	

}
