package com.example.project.controller;

import com.example.project.entity.Module;
import com.example.project.entity.Post;
import com.example.project.result.Result;
import com.example.project.service.IModuleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ModuleController {
    @Autowired
    private IModuleService iModuleService;

    @CrossOrigin
    @ApiOperation("After click the dropdown menu of 'mudule' at the navbar" +
            "choice=1-->life choice=2-->knowledge")
    @RequestMapping(value = "/api/getmodule",method = RequestMethod.GET)
    @ResponseBody
    public Result getmodule(@RequestParam("choice")int choice){
        Result result;
        if(choice ==1){
            //get post list of life module
            List<Post> posts = iModuleService.getPosts("life");
            result = Result.ok(posts);
        }
        else if(choice == 2){
            //get module list of knowledge module
            List<Module> modules = iModuleService.getModules();
            result = Result.ok(modules);
        }
        else{
            result = new Result(400,"Unknown Command",null);
        }
        return result;
    }

    @CrossOrigin
    @ApiOperation("Search Knowledge Module via search key")
    @RequestMapping(value = "/api/searchmodule",method = RequestMethod.GET)
    @ResponseBody
    public Result searchmodule(@RequestParam("key")String key){
        List<Module> modules = iModuleService.searchModule(key);
        return Result.ok(modules);
    }

    @CrossOrigin
    @ApiOperation("Get Posts of a mudule by its module_name")
    @RequestMapping(value = "/api/getposts",method = RequestMethod.GET)
    @ResponseBody
    public Result getposts(@RequestParam("module_name")String module_name){
        List<Post> posts = iModuleService.getPosts(module_name);
        return Result.ok(posts);
    }


    @CrossOrigin
    @ApiOperation("Get user's point at a module")
    @RequestMapping(value="/api/getpoints", method = RequestMethod.POST)
    @ResponseBody
    //request params: module_name of life module: "life", when frontend send http request
    public Result getpoints(@RequestParam("username")String username,
                            @RequestParam("module_name")String module_name){
        int point = iModuleService.getPoints(username,module_name);
        return Result.ok(point);
    }

    @CrossOrigin
    @ApiOperation("Apply for manager of a module")
    @RequestMapping(value = "/api/applym", method = RequestMethod.POST)
    @ResponseBody
    public Result applym(@RequestParam("username")String username,
                         @RequestParam("module_name")String module_name){
        int res = iModuleService.applym(username,module_name);
        if(res == 0)
            return Result.ok("apply successfully");
        else if(res == 1)
            return Result.fail("you have been manager of certain module");
        else if(res ==2)
            return Result.fail("the module has had enough managers");
        else//res =3
            return Result.fail("dont have enough points to apply for a manager");
    }

}