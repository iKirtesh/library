package com.example.booklab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booklab.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
