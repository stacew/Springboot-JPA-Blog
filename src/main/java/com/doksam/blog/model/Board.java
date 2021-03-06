package com.doksam.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob//대용량 데이터
	private String content;//섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 되기 때문에 대용량
	
	// 강제로 세팅.. 한다고.. @ColumnDefault("0")
	private int count; //조회수
	
	//private int userId; // DB는 FK를 쓴다. 오브젝트를 저장할 수 없다.
	@ManyToOne(fetch=FetchType.EAGER) // This = Many, Target = One
	@JoinColumn(name="userId")
	private User user; //자바는 오브젝트를 저장할 수 있다

	
	@OneToMany(mappedBy = "board", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	//mappedBy가 있으면 연관관계의 주인이 아니다. => FK가 아니다. => DB에 컬럼을 만들지 마세요.
	//cascade REMOVE 보드가 삭제될 때, replys를 전부 삭제
	//@JoIncolumn(name="replyId") 필요 없음. 테이블을 만들게 되면 1정규화가 깨지기 때문에, join할 때 값만 넣어야 함.
	@JsonIgnoreProperties({"board"}) //jackson json 처리 시, board에서 replys를 호출할 때, board의 getter 무시된다.
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
}
