package com.doksam.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCreateRequestDto {
	private int boardId;
	private String content;
}
//replyCreate할 때, ajax Data를 해당 방식으로 보낸다면, 파라미터가 단순해지는 장점이 있음.
//user id는 서버에서 관리하는 principal이 있어서 뺐음. 
