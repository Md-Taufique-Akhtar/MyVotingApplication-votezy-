package com.jspider.votezyrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jspider.votezy.entity.Vote;
@Repository
public interface VoteRepositiory extends JpaRepository<Vote, Long> {

}
