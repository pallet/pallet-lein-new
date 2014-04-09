# lein-pallet-new

A [leiningen 2.x][lein] plugin for creating [pallet][palletops] pallet
infrastructure projects.

## Usage

To create a new project using leiningen:,

```bash
lein new pallet my-project
```

### Options

By default, the latest recommended versions of the libraries will be
used. In case you need to use different versions, you can use the
following options:

 - `with-pallet` <version>
 - `with-pallet-jclouds` <version>
 - `with-pallet-vmfest` <true/false> :whether to use vmfest
 - `with-vmfest` <version>
 - `with-jclouds` <version>

For example the following command will build the project using pallet
version 0.8.0-RC.4 and pallet-vmfest 0.4.0-alpha.1:

```bash
lein new pallet my-project with-pallet 0.8.0-RC.4 with-pallet-vmfest 0.4.0-alpha.1 
```

## License

Copyright (C) 2012, 2013 Hugo Duncan

Distributed under the Eclipse Public License.

[lein]: https://github.com/technomancy/leiningen "Leiningen Clojure Build Tool"
[palletops]: https://palletops.com/ "PalletOps Site"
