package com.example.booklab.service;

import com.example.booklab.dto.BookResponse;
import com.example.booklab.dto.MemberRequest;
import com.example.booklab.dto.MemberResponse;
import com.example.booklab.exception.ResourceNotFoundException;
import com.example.booklab.model.Member;
import com.example.booklab.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    @Transactional
    public MemberResponse addMember(MemberRequest memberRequest) {
        Member member = modelMapper.map(memberRequest, Member.class);
        return convertToDto(memberRepository.save(member));
    }

    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::convertToDto).toList();
    }

    public MemberResponse getMemberById(Long id) {
        Member member = findMemberById(id);
        return convertToDto(member);
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = findMemberById(id);
        if (!member.getBooks().isEmpty()) {
            throw new IllegalStateException("Cannot delete member with issued books");
        }
        memberRepository.delete(member);
    }

    public List<BookResponse> getBorrowedBooks(Long memberId) {
        Member member = findMemberById(memberId);
        return member.getBooks().stream()
                .map(bookService::convertToDto).toList();
    }

    private Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
    }

    private MemberResponse convertToDto(Member member) {
        MemberResponse response = modelMapper.map(member, MemberResponse.class);
        response.setBorrowedBooks(
            member.getBooks().stream()
                .map(bookService::convertToDto).toList()
        );
        return response;
    }
}
