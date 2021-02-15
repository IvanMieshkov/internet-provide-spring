<#import "fragments/pagination.ftl" as pagination/>
<#import "fragments/error.ftl" as error>

<head>
    <#include "fragments/bootstrap.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>${service}</title>
</head>

<body>
<#include "fragments/nav-bar.ftl" />
<br>

<div class="container">
    <div class="row">
        <h2 class="text-danger"><@spring.message "title.tariff.list"/> ${service}</h2>

        <table class="table table-bordered success">
            <thead>
            <tr>
                <@error.error/>
                <th><@spring.message "title.tariff.name"/>
                    <#if springMacroRequestContext.getLocale().toString()?contains("en")>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=nameEn,ASC">&#8593</a>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=nameEn,DESC">&#8595</a>
                    <#else>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=nameUkr,ASC">&#8593</a>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=nameUkr,DESC">&#8595</a>
                    </#if>
                </th>
                <th>  <@spring.message "title.tariff.price"/>
                    <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=price,ASC">&#8593</a>
                    <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=price,DESC">&#8595</a>
                </th>
            </tr>
            </thead>
            <tbody>
            <#list page.content as tariff>
                <tr>
                    <th>
                        <#if springMacroRequestContext.getLocale().toString()?contains("en")>
                            ${tariff.nameEn}
                        <#else>
                            ${tariff.nameUkr}
                        </#if>
                    </th>
                    <td>${tariff.price}</td>
                    <#if isAdmin>
                        <td>
                            <a href="/admin/tariff-edit/${tariff.id}"> <@spring.message "button.change"/></button>
                            </a>
                        </td>
                    <#else>
                        <#if usersTariffs?seq_contains(tariff)>

                            <td>
                                <a><@spring.message "button.choosen"/></a>
                            </td>
                        <#else>
                            <td>
                                <form class="form-inline my-2 mr-2 my-lg-0"
                                      action="/client/add-tariff/${tariff.id}" method="post">
                                    <button type="submit" class="btn btn-sm btn-outline-secondary"><@spring.message "button.choose"/></button>
                                </form>
                            </td>
                        </#if>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="col-sm">
            <#if isAdmin>
                <a href="/admin/tariff-create/${service}" class="btn btn-link">
                    <button class="btn btn-primary btn-xs" type="submit">
                        <@spring.message "button.add.tariff"/></button>
                </a>
            </#if>
        </div>
        <div class="col-sm">
            <a href="/pdf/${service}" class="btn btn-link">
                <button class="btn btn-primary btn-xs" type="submit"><@spring.message "button.pdf"/></button>
            </a>
        </div>
    </div>
    <div class="form-row text-center">
        <div class="col-12">
            <a ><@pagination.pagination service page/></a>
        </div>
    </div>
</div>

<#include "fragments/footer.ftlh"/>
</body>