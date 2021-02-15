<#include "security.ftlh"/>

<#import "/spring.ftl" as spring/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <#if isAdmin>
        <a class="navbar-brand" href="/admin/menu"><@spring.message "title.index"/></a>
    </#if>
    <#if !isAdmin>
        <a class="navbar-brand" href="/client/menu"><@spring.message "title.index"/></a>
    </#if>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <#if know>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/tariff-list/internet">
                        <@spring.message "navbar.internet"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/tariff-list/tv">
                        <@spring.message "navbar.tv"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/tariff-list/telephony">
                        <@spring.message "navbar.telephony"/>
                    </a>
                </li>
                <li class="nav-item ml-2">
                    <form class="form-inline" action="/auth/logout" method="post">
                        <button class="btn btn-link my-2 my-sm-0" type="submit">
                            <@spring.message "navbar.logout"/>
                        </button>
                    </form>
                </li>
                <li class="nav-item ml-2">
                    <div class="btn-group">
                        <button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <@spring.message "navbar.change.language"/>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${springMacroRequestContext.requestUri}?lang=en"><@spring.message "navbar.english"/></a>
                            <a class="dropdown-item" href="${springMacroRequestContext.requestUri}?lang=ua"><@spring.message "navbar.ukrainian"/></a>

                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="navbar-text"><@spring.message "navbar.logged.user"/> ${login}</div>

    </#if>
</nav>
