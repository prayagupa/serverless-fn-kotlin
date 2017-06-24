 (defproject amz-wavelength "1.0-SNAPSHOT"
  :description "playing with Amz"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.taoensso/encore "2.68.0"]
                 [org.clojure/core.async "0.2.374"]
                 [amazonica "0.3.104"]
                 [org.clojure/data.json "0.2.6"]
                 [uswitch/lambada "0.1.2"]
                 ;;[org.onyxplatform/onyx-kafka "0.10.0.0-rc2"]
]
  ;;:profiles {:uberjar {:aot :all}}
  ;;:uberjar-name "amz-wavelength-1.0-SNAPSHOT.jar")
)
