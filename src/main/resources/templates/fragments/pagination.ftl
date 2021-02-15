<#import "/spring.ftl" as spring/>
<#macro pagination url page>

    <#if page.getTotalPages() gt  7>
        <#assign
        totalPages = page.getTotalPages()
        pageNumber = page.getNumber()+1

        head =(pageNumber gt 4)?then([1,-1],[1,2,3])
        tail = (pageNumber lt (totalPages-3))?then([-1,totalPages],[totalPages-2,totalPages-1,totalPages])
        bodyBefore =((pageNumber gt 4)&&(pageNumber lt totalPages-1))?then([pageNumber-2,pageNumber-1],[])
        bodyAfter =((pageNumber gt 2)&&(pageNumber lt totalPages-3))?then([pageNumber+1,pageNumber+2],[])

        body= head + bodyBefore + ((pageNumber gt 3)&&(pageNumber lt totalPages-2))?then([pageNumber],[])+bodyAfter+tail
        >
    <#else >

        <#if page.getTotalPages()==0>
            <#assign  body=1..1/>
        <#else>
            <#assign  body=1..page.getTotalPages()  />
        </#if>



    </#if>
    <div class="mt-3">
        <ul class="pagination ">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><@spring.message"pagination.page"/></a>
            </li>
            <#list  body as pn>
                <#if (pn-1)==page.getNumber()>
                    <li class="page-item active">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">${pn}</a>
                    </li>
                <#elseif pn=-1>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">...</a>
                    </li>

                <#else >
                    <li class="page-item">
                        <a class="page-link" href="${url}?page=${pn-1}&size=${page.getSize()}" tabindex="-1"
                           aria-disabled="true">${pn}</a>
                    </li>
                </#if>
            </#list>

        </ul>
    </div>

</#macro>