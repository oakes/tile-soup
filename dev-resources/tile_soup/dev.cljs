(ns tile-soup.dev
  (:require [tile-soup.core :as c]
            [orchestra-cljs.spec.test :as st]
            [expound.alpha :as expound]
            [clojure.spec.alpha :as s])
  (:require-macros [tile-soup.dev :refer [read-public]]))

(st/instrument)
(set! s/*explain-out* expound/printer)

(println (pr-str (c/parse-map (read-public "level1.tmx"))))
