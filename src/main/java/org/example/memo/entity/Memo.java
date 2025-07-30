package org.example.memo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.memo.dto.MemoRequestDto;

@Getter
@AllArgsConstructor
public class Memo {

    private Long id;
    private String title;
    private String content;

    public void update(MemoRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

}
