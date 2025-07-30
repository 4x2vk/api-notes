package org.example.memo.controller;

import org.example.memo.dto.MemoRequestDto;
import org.example.memo.dto.MemoResponseDto;
import org.example.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContent());
        memoList.put(memoId, memo);
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }
    @GetMapping
    public List<MemoResponseDto> findAllMemos() {
        List<MemoResponseDto> responseList = new ArrayList<>();

        for (Memo memo : memoList.values()) {
            MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
            responseList.add(memoResponseDto);
        }

//        responseList = memoList.values().stream().map(MemoResponseDto::new).toList();
        return responseList;
    }

    @GetMapping("/{id}")
    public MemoResponseDto findMemoById(@PathVariable Long id) {
        Memo memo = memoList.get(id);
        return new MemoResponseDto(memo);
    }
    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ) {
        Memo memo = memoList.get(id);
        memo.update(dto);
        return new MemoResponseDto(memo);
    }
    @DeleteMapping("/{id}")
    public void deleteMemoById(@PathVariable Long id) {
        memoList.remove(id);
    }
}
