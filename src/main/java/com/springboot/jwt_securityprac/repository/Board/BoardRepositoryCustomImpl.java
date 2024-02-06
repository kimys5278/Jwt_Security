package com.springboot.jwt_securityprac.repository.Board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.jwt_securityprac.data.entity.Board;

import com.springboot.jwt_securityprac.data.entity.QBoard;
import com.springboot.jwt_securityprac.data.entity.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> findBoardsByUsername(String name) {
        QBoard board = QBoard.board;
        QUser user = QUser.user;

        return jpaQueryFactory.selectFrom(board)
                //Board 엔티티 내에 정의된 User 엔티티에 대한 참조(외래 키를 통한 연관 관계)입니다
                .join(board.user,user)
                .where(user.name.eq(name))
                .fetch();

    }

//    @Override
//    public long increaseViewCount(Long id) {
//        QBoard board = QBoard.board;
//        return jpaQueryFactory.update(board)
//                .set(board.view,board.view.add(1))
//                .where(board.id.eq(id))
//                .execute();
//    }


}
