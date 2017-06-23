(def project 'com.kenbier/victory-cljs)
(def version "0.1.0")

(set-env! :resource-paths #{"src"}
          :dependencies   '[[org.clojure/clojure "1.8.0" :scope "provided"]
                            [org.clojure/clojurescript "1.7.228" :scope "provided"]
                            [adzerk/bootlaces "0.1.13" :scope "test"]

                            [sablono "0.8.0"]
                            [cljsjs/victory "0.17.0-0"]
                            [camel-snake-kebab "0.4.0"]])

(require
 '[adzerk.bootlaces :refer :all])

(task-options!
 pom {:project     project
      :version     version
      :description "A ClojureScript wrapper for Victory Charts."
      :scm         {:url "https://github.com/kenbier/victory-cljs"}
      :license     {"The MIT License (MIT)"
                    "https://opensource.org/licenses/MIT"}}
 push {:repo "deploy-clojars"})

(deftask build
  "Build and install the project locally."
  []
  (comp (pom) (jar) (install)))

