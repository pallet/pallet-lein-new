# Release Notes

## 0.2.9

- Update to pallet 0.7.3

## 0.2.8

- Add logging block for clj-ssh
  add the block to log clj-ssh to the compute logfile and a comment making
  it clear how to run on dubugging for ssh keys.

- s/sanitized/name/ in repl.clj. Fixes #2

## 0.2.7

- Add workaround for commons-logging in repl task
  The lein repl task adds commons-logging to our classpath, messing up the
  logging configuration. This forces use of slf4j.

## 0.2.6

- Update to latest release versions
  pallet 0.7.2 lein-pallet 0.5.2 pallet-jclouds 1.4.2

- Add test jar to lein2-style dependencies

## 0.2.5

- Update to pallet-lein 0.5.1

## 0.2.4

- Fix project.clj to use :plugins for the pallet plugin

## 0.2.3

- Update to pallet 0.7.1

## 0.2.2

- This is identical to 0.2.1, but is built with lein 1.x, to ensure that
  it is compatible with lein 1.x plugin install.

## 0.2.1

- Add pallet-lein to generated project.clj

### Known Issues

- Does not work with lein 1.x

## 0.2.0

- Update pallet version to 0.7.0

- Rename artifact to pallet/lein-template
  Lein 2 will automatically resolve this artifact name when invoked with
  'lein new pallet'

- Update default to pallet-vmfest 0.2.0-beta.3

- Update to pallet-jclouds 1.3.0-beta.1

- Reduce the default log levels for jclouds to INFO

- Add allblobstore as a jclouds dependency

- Add clojure 1.3.0 as a dependency
  the add-service task seems to dislike clojure 1.2.1

- Remove pallet 0.6.x support

## 0.1.0

Initial release
