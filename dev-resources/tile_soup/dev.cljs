(ns tile-soup.dev
  (:require [tile-soup.core :as c]
            [orchestra-cljs.spec.test :as st]
            [expound.alpha :as expound]
            [clojure.spec.alpha :as s])
  (:require-macros [tile-soup.dev :refer [read-public parse]]))

(st/instrument)
(set! s/*explain-out* expound/printer)

(println (pr-str (c/parse (read-public "koalio-b64.tmx"))))
;(println (pr-str (parse "koalio-b64.tmx")))

;(println (pr-str (c/parse (read-public "koalio-xml.tmx"))))
;(println (pr-str (parse "koalio-xml.tmx")))

;(println (pr-str (c/parse (read-public "koalio-csv.tmx"))))
;(println (pr-str (parse "koalio-csv.tmx")))

;(println (pr-str (c/parse (read-public "koalio-b64-gzip.tmx"))))
;(println (pr-str (parse "koalio-b64-gzip.tmx")))

;(println (pr-str (c/parse (read-public "tmx/test_hexagonal_tile_60x60x30.tmx"))))

#_
(doseq [xml [(read-public "tmx/desert.tmx")
             (read-public "tmx/orthogonal-outside.tmx")
             (read-public "tmx/hexagonal-mini.tmx")
             (read-public "tmx/perspective_walls.tmx")
             (read-public "tmx/isometric_grass_and_water.tmx")
             (read-public "tmx/sewers.tmx")
             (read-public "tmx/isometric_staggered_grass_and_water.tmx")
             (read-public "tmx/test_hexagonal_tile_60x60x30.tmx")]]
  (println (pr-str (c/parse xml))))

