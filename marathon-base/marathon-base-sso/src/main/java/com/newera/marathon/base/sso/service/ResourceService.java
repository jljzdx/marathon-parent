package com.newera.marathon.base.sso.service;

import java.util.List;

public interface ResourceService
{

    List<String> findByUserId(String userName);
}
