import { useMediaQuery } from "react-responsive";

export const Mobile = ({children}) => {
    const isMobile = useMediaQuery({ query: "(max-width: 1120px)" });

    return <>{isMobile && children}</>
}

export const Tablet = ({children}) => {
    const isTablet = useMediaQuery({ query: "(min-width:769px) and (max-width:1280px)" });

    return <>{isTablet && children}</>
}

export const PC = ({children}) => {
    const isPc = useMediaQuery({ query: "(min-width: 1281px)" });

    return <>{isPc && children}</>
}