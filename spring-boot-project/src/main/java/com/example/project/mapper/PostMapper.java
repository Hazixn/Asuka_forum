package com.example.project.mapper;

import com.example.project.entity.Post;
import org.apache.ibatis.annotations.Param;

public interface PostMapper {
    Post getPost(@Param(value = "post_id")int post_id);
    //whether the user has liked this post
    int existLike(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    void insertLike(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    void deleteLike(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    //whether the user has collected this post
    int existCollect(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    void insertCollect(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    void deleteCollect(@Param(value = "post_id")int post_id,@Param(value = "username")String username);

    //whether there is a record in module_point table
    int existPoint(@Param(value = "username")String username,@Param(value = "module_name")String module_name);
    void insertPoint(@Param(value = "username")String username,
                     @Param(value = "module_name")String module_name,
                     @Param(value = "point")int point);

    //update table post
    void updateP(@Param(value = "post_id")int post_id,@Param(value = "choice")int choice);
    //update point at table module_point
    void updatePoint(@Param(value = "username")String username,
                     @Param(value = "module_name")String module_name,
                     @Param(value = "point")int point);

}
