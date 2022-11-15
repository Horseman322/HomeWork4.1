package com.example.homework36.controller;


import com.example.homework36.service.AvatarService;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/avatars")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }
    @PostMapping
    public void uploadAvatar(@RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(avatar);
    }

    @GetMapping("/{id}/read-from-db")
    public ResponseEntity<byte[]> readAvatarFromDb(@PathVariable long id){
        Pair<String, byte[]> pair = avatarService.readAvatarFromDb(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pair.getFirst()))
                .contentLength(pair.getSecond().length)
                .body(pair.getSecond());
    }

    @GetMapping("/{id}/read-from-fs")
    public ResponseEntity<byte[]> readAvatarFromFs(@PathVariable long id) throws IOException {
        Pair<String, byte[]> pair = avatarService.readAvatarFromFs(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pair.getFirst()))
                .contentLength(pair.getSecond().length)
                .body(pair.getSecond());
    }

}
