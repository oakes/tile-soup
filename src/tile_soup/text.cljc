(ns tile-soup.text
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::fontfamily string?)
(s/def ::pixelsize u/str->int)
(s/def ::wrap u/str->boolean)
(s/def ::color string?)
(s/def ::bold u/str->boolean)
(s/def ::italic u/str->boolean)
(s/def ::underline u/str->boolean)
(s/def ::strikeout u/str->boolean)
(s/def ::kerning u/str->boolean)
(s/def ::halign #{"left" "center" "right" "justify"})
(s/def ::valign #{"top" "center" "bottom"})

(s/def ::attrs (s/keys :req-un [::fontfamily
                                ::pixelsize
                                ::wrap
                                ::color
                                ::bold
                                ::italic
                                ::underline
                                ::strikeout
                                ::kerning
                                ::halign
                                ::valign]))

(s/def ::text (s/keys :req-un [::attrs]))

