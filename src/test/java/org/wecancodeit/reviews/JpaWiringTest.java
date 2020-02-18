package org.wecancodeit.reviews;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wecancodeit.reviews.Models.Category;
import org.wecancodeit.reviews.Models.CategoryRepository;
import org.wecancodeit.reviews.Models.Review;
import org.wecancodeit.reviews.Models.ReviewRepository;

import java.awt.print.Book;
import java.util.Optional;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void categoryShouldHaveAListOfReviews() {
        Category testCategory = new Category("Red Wine", 34, "Flannigan");
        Review testReview = new Review(testCategory, "Author", "reviewtext", 0);
        categoryRepo.save(testCategory);
        reviewRepo.save(testReview);
        entityManager.flush();
        entityManager.clear();
        Optional<Category> retrievedCategoryOptional = categoryRepo.findCategoryById(testCategory.getId());
        Category retrievedCategory = retrievedCategoryOptional.get();
        Review retrievedReview = reviewRepo.findById(testReview.getid()).get();
        assertThat(retrievedReview.getReview()).contains(testRevkew);
    }

}