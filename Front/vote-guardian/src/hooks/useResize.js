import { useMediaQuery } from "react-responsive";

export const Mobile = ({children}) => {
    const isMobile = useMediaQuery({ query: "(max-width: 1218px)" });

    return <>{isMobile && children}</>
}

export const Tablet = ({children}) => {
    const isTablet = useMediaQuery({ query: "(min-width:1219px) and (max-width:1500px)" });

    return <>{isTablet && children}</>
}

export const PC = ({children}) => {
    const isPc = useMediaQuery({ query: "(min-width: 1501px)" });

    return <>{isPc && children}</>
}