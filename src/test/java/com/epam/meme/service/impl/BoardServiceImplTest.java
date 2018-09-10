package com.epam.meme.service.impl;

import com.epam.meme.config.logic.ApplicationConfiguration;
import com.epam.meme.entity.Board;
import com.epam.meme.entity.User;
import com.epam.meme.service.BoardService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@Transactional
public class BoardServiceImplTest {

    @Autowired
    private BoardService service;

    @Test
    public void findById_found() {
        Assert.assertTrue(service.findById(1L).isPresent());
    }

    @Test
    public void findById_notFound() {
        Assert.assertFalse(service.findById(Long.MAX_VALUE).isPresent());
    }

    @Test
    public void create_created() {
        Board board = Board.builder().id(10L).name("name").admin(
                User.builder().id(10L).email("email").username("username").password("password").build())
                .build();

        service.create(board);
    }

    @Test
    public void update_updated() {
        Board board = service.findById(1L).orElseThrow(RuntimeException::new);
        board.setName("newname");
        service.update(board);
        Assert.assertEquals("newname", service.findById(1L).orElseThrow(RuntimeException::new).getName());
    }

    @Test(expected = Exception.class)
    public void updated_notUpdated() {
        Board board = new Board();
        service.update(board);
    }

    @Test
    public void delete_deleted() {
        Board board = service.findById(1L).orElseThrow(RuntimeException::new);
        service.delete(board);
    }

    //@Test(expected = Exception.class)
    public void delete_notDeleted() {
        Board board = new Board();
        service.delete(board);
    }
}