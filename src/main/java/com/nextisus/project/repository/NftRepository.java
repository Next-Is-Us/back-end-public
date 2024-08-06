package com.nextisus.project.repository;

import com.nextisus.project.domain.Nft;
import com.nextisus.project.domain.User;
import com.nextisus.project.exception.ntf.NftNotFoundException;
import com.nextisus.project.exception.ntf.UserNftNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {

    Optional<Nft> findByNftId(Long nftId);
    List<Nft> findAllByUser_Id(Long userNftId);

    Long countByUser(User user);

    default Nft getByNft(Long nftId) {
        return findByNftId(nftId).orElseThrow(NftNotFoundException::new);
    }

    default List<Nft> getAllByUserId(Long userId) {
        List<Nft> nfts = findAllByUser_Id(userId);
        if(nfts.isEmpty()) {
            throw new UserNftNotFoundException();
        }
        return nfts;
    }

    List<Nft> findAllByHealthRecordIsNullAndUser_Id(Long userId);
    Long countByUser_Id(Long userId);
}
