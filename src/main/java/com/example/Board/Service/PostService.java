//package com.example.Board.Service;
//
//
//import com.example.Board.entity.Post;
//import com.example.Board.repository.PostRepository;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PostService {
//    private final PostRepository postRepository;
//    public PostService(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
//    public Page<Post> getPosts(int pageNum, int showNum){
//        Pageable pageable = PageRequest.of(pageNum, showNum, Sort.by("createdAt").descending());
//        return postRepository.findAllByOrderByCreatedAtDesc(pageable);
//    }
//    public Post savePost(Post post) {
//        return postRepository.save(post);
//    }
//}
