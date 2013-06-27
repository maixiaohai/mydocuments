(setq user-full-name "zhangxu")
(setq user-mail-address "zhangxu@admaster.com.cn")

;;some basic settings
(setq inhibit-startup-message t)

;;(add-to-list 'load-path “~/.emacs.d/”)
;;(add-to-list 'load-path “~/.emacs.d/config/”)

(setq default-tab-width 2)
(setq tab-width 2)

(fset 'yes-or-no-p 'y-or-n-p)

(require 'linum)
(global-linum-mode t)

(show-paren-mode t)

(setq-default make-backup-files nil)

(require 'ido)
(ido-mode t)
(setq ido-enable-flex-matching);;enable fuzzy matching

(add-to-list 'load-path "~/.emacs.d/config/autocomplete/")
(require 'auto-complete-config)
(add-to-list 'ac-dictionary-directories "~/.emacs.d/config/autocomplete//ac-dict")
(ac-config-default)

(load-file "/home/zhangxu/.piglatin.el")

(setq load-path (cons (expand-file-name "~/.emacs.d/") load-path))
(require 'go-mode-load)

;;coffee-mode
(add-to-list 'load-path "~/.emacs.d/coffee-mode")
(require 'coffee-mode)
(require 'flymake-easy)
(require 'flymake-coffee)
(add-hook 'coffee-mode-hook 'flymake-coffee-load)

;;jshint
;;(add-to-list 'load-path' "~/.emacs.d/jshint-mode")
;;(require 'flymake-jshint')
;;(add-hook 'javascript-mode-hook
;;					(lambda () (flymake-mode t)))










