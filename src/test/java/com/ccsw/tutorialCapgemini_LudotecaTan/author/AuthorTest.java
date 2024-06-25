/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorialCapgemini_LudotecaTan.author.model.Author;
import com.ccsw.tutorialCapgemini_LudotecaTan.author.repository.AuthorRepository;
import com.ccsw.tutorialCapgemini_LudotecaTan.author.service.AuthorServiceImpl;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 25 jun 2024
 * @lastModified 25 jun 2024
 * @version 1.0
 *
 **/


@ExtendWith(MockitoExtension.class)
public class AuthorTest {

    public static final Long EXISTS_AUTHOR_ID = 1L;
    public static final Long NOT_EXISTS_AUTHOR_ID = 0L;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void getExistsAuthorIdShouldReturnAuthor() {

          Author author = mock(Author.class);
          when(author.getId()).thenReturn(EXISTS_AUTHOR_ID);
          when(authorRepository.findById(EXISTS_AUTHOR_ID)).thenReturn(Optional.of(author));

          Author authorResponse = authorService.get(EXISTS_AUTHOR_ID);

          assertNotNull(authorResponse);

          assertEquals(EXISTS_AUTHOR_ID, authorResponse.getId());
    }

    @Test
    public void getNotExistsAuthorIdShouldReturnNull() {

          when(authorRepository.findById(NOT_EXISTS_AUTHOR_ID)).thenReturn(Optional.empty());

          Author author = authorService.get(NOT_EXISTS_AUTHOR_ID);

          assertNull(author);
    }

}
