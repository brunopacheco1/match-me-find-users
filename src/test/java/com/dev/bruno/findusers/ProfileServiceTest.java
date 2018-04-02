package com.dev.bruno.findusers;

import com.dev.bruno.findusers.app.Application;
import com.dev.bruno.findusers.model.Profile;
import com.dev.bruno.findusers.service.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProfileServiceTest {

    @Autowired
    private ProfileService service;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Profile.class);
        esTemplate.createIndex(Profile.class);
        esTemplate.putMapping(Profile.class);
        esTemplate.refresh(Profile.class);
    }

    @Test
    public void testSave() {

        Profile profile = new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        Profile testProfile = service.save(profile);

        assertNotNull(testProfile.getId());
        assertEquals(testProfile.getName(), profile.getName());
        assertEquals(testProfile.getUsername(), profile.getUsername());
        assertEquals(testProfile.getLocation(), profile.getLocation());

    }

    @Test
    public void testFindOne() {

        Profile profile = new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        service.save(profile);

        Profile testProfile = service.findOne(profile.getId());

        assertNotNull(testProfile.getId());
        assertEquals(testProfile.getName(), profile.getName());
        assertEquals(testProfile.getUsername(), profile.getUsername());
        assertEquals(testProfile.getLocation(), profile.getLocation());

    }

    @Test
    public void testFindByTitle() {

        Profile profile = new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        service.save(profile);

        List<Profile> byTitle = service.findByName(profile.getName());
        assertThat(byTitle.size(), is(1));
    }

    @Test
    public void testFindByAuthor() {

        List<Profile> profileList = new ArrayList<>();

        profileList.add(new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        profileList.add(new Profile("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        profileList.add(new Profile("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
        profileList.add(new Profile("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
        profileList.add(new Profile("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));

        profileList.forEach(profile -> {
            service.save(profile);
        });

        Page<Profile> byLocation = service.findByLocation("Rambabu Posa", new PageRequest(0, 10));
        assertThat(byLocation.getTotalElements(), is(4L));

        Page<Profile> byLocation2 = service.findByLocation("Mkyong", new PageRequest(0, 10));
        assertThat(byLocation2.getTotalElements(), is(1L));

    }

    @Test
    public void testDelete() {

        Profile profile = new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        service.save(profile);
        service.delete(profile);
        Profile testProfile = service.findOne(profile.getId());
        assertNull(testProfile);
    }
}
