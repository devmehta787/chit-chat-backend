/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Copyright (C) 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.User;
import models.UserDto;
import ninja.jpa.UnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;


public class UserDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    

    @Transactional
    public UserDto addNewUser(UserDto userDto) {
        
        EntityManager entityManager = entityManagerProvider.get();
        
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFullname(userDto.getFullname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        
        entityManager.persist(user);
        
        return userDto;
        
    }
    @UnitOfWork
    public User isUserAndPasswordValid(String username, String password) {

        if (username != null && password != null) {

            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<User> q = entityManager.createQuery("SELECT x FROM User x WHERE username = :usernameParam",
                    User.class);
            User user = getSingleResult(q.setParameter("usernameParam", username));

            if (user != null) {

                if (user.password.equals(password)) {

                    return user;
                }

            }

        }
        return null;
    }

    public User getUserByName(String name) {
        EntityManager entityManager = entityManagerProvider.get();
        List<User> q = entityManager.createQuery("SELECT x FROM User x WHERE username = :nameparam",
                User.class)
                .setParameter("nameparam", name)
                .getResultList();
        User user = q.get(0);
        // User user = getSingleResult(q.setParameter("usernameParam", name));
        // UserDto userDto = new UserDto();
        // userDto.setUsername(user.getUsername());
        // userDto.setPassword(user.getPassword());
        // userDto.setFullname(user.getFullname());
        // userDto.setEmail(user.getEmail());
        // userDto.setPhone(user.getPhone());
        return user;
    }
    

    public List<User> getUser() {
        EntityManager entityManager = entityManagerProvider.get();
        List<User> q = entityManager.createQuery("SELECT x FROM User x",
                User.class).getResultList();
        // List<User> user = q.get(0);
        return q;
    }

    /**
     * Get single result without throwing NoResultException, you can of course just catch the
     * exception and return null, it's up to you.
     */
    private static <T> T getSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

}
