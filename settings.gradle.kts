rootProject.name = "sharp"

include("domain")
include("service")
include("common")

include("infrastructure:sharp-mariadb")

include("api:inventory-api")

include("common:security")
include("common:api-common")
