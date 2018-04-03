package com.dev.bruno.findusers;

import com.dev.bruno.findusers.app.Application;
import com.dev.bruno.findusers.model.Profile;
import com.dev.bruno.findusers.service.ProfileService;
import org.elasticsearch.common.geo.GeoPoint;
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
    private ElasticsearchTemplate elasticTemplate;

    @Before
    public void before() {
        elasticTemplate.deleteIndex(Profile.class);
        elasticTemplate.createIndex(Profile.class);
        elasticTemplate.putMapping(Profile.class);
        elasticTemplate.refresh(Profile.class);
    }

    @Test
    public void testSave() {

        Profile profile = new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", new GeoPoint(51.500152D, -0.126236D));
        Profile testProfile = service.save(profile);

        assertNotNull(testProfile.getId());
        assertEquals(testProfile.getName(), profile.getName());
        assertEquals(testProfile.getUsername(), profile.getUsername());
        assertEquals(testProfile.getLocation(), profile.getLocation());

    }

    @Test
    public void testFindOne() {

        Profile profile = new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", new GeoPoint(51.500152D, -0.126236D));
        service.save(profile);

        Profile testProfile = service.findOne(profile.getId());

        assertNotNull(testProfile.getId());
        assertEquals(testProfile.getName(), profile.getName());
        assertEquals(testProfile.getUsername(), profile.getUsername());
        assertEquals(testProfile.getLocation(), profile.getLocation());

    }

    @Test
    public void testFindByUsername() {

        Profile profile = new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", new GeoPoint(51.500152D, -0.126236D));
        service.save(profile);

        List<Profile> byUsername = service.findByUsername(profile.getUsername());
        assertThat(byUsername.size(), is(1));
    }

    @Test
    public void testFindByName() {

        List<Profile> profileList = new ArrayList<>();

        profileList.add(new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", new GeoPoint(51.500152D, -0.126236D)));
        profileList.add(new Profile("1002", "Apache Lucene Basics", "Rambabu Posa", new GeoPoint(51.500152D, -0.126236D)));
        profileList.add(new Profile("1003", "Apache Solr Basics", "Rambabu Posa", new GeoPoint(51.500152D, -0.126236D)));
        profileList.add(new Profile("1007", "Spring Data + ElasticSearch", "Rambabu Posa", new GeoPoint(51.500152D, -0.126236D)));
        profileList.add(new Profile("1008", "Spring Boot + MongoDB", "Mkyong", new GeoPoint(51.500152D, -0.126236D)));

        profileList.forEach(profile -> {
            service.save(profile);
        });

        Page<Profile> byName = service.findByName("Elasticsearch", PageRequest.of(0, 10));
        assertThat(byName.getTotalElements(), is(2L));

        Page<Profile> byName2 = service.findByName("Apache", PageRequest.of(0, 10));
        assertThat(byName2.getTotalElements(), is(2L));

    }

    @Test
    public void testDelete() {

        Profile profile = new Profile("1001", "Elasticsearch Basics", "Rambabu Posa", new GeoPoint(51.500152D, -0.126236D));
        service.save(profile);
        service.delete(profile);

        Profile testProfile = service.findOne(profile.getId());

        assertNull(testProfile);
    }
}
