package com.lqk.springbootproject01.controller;

import com.lqk.springbootproject01.dto.AccessTokenDTO;
import com.lqk.springbootproject01.dto.GithubUser;
import com.lqk.springbootproject01.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.setClient.id}")
    private String Client_id;

    @Value("${github.setClient.secre}")
    private String Client_secret;

    @Value("${github.setRedirect.uri}")
    private String Redirect_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        String accessToken = githubProvider.getAccessTokenDTO(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.print(user.getId());
        return "index";
    }
}
