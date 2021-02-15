<#import "/spring.ftl" as spring/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/"><@spring.message "title.index"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div align="left" class="collapse navbar-collapse" id="navbarSupportedContent">
        <div class="nav-item dropdown">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><@spring.message "navbar.change.language"/>
                        <b class="caret"></b></a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item"  href="${springMacroRequestContext.requestUri}?lang=en"><@spring.message "navbar.english"/></a>
                        <a class="dropdown-item"  href="${springMacroRequestContext.requestUri}?lang=ua"><@spring.message "navbar.ukrainian"/></a>
                    </div>

                </li>
            </ul>
        </div>

    </div>
</nav>