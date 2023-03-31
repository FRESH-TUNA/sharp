rootProject.name = "sharp"

include("domain")
include("service")
include("common")

include("infrastructure:sharp-mariadb")

include("api:stock-api")

include("common:security")
include("common:api-common")
