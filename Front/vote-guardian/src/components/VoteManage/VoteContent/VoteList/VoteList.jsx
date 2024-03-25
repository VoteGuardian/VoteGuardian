import VoteOne from "../VoteOne/VoteOne";
import './VoteList.scss';
import { Mobile, PC, Tablet } from "@/hooks/useResize";

export default function VoteList() {
    return (
        <>
            <PC>
                <div className="pc-vote-list">
                    <VoteOne/>
                </div>
            </PC>
            <Tablet>
                <div className="tablet-vote-list">
                    <VoteOne/>
                </div>
            </Tablet>
            <Mobile>
                <div className="mobile-vote-list">
                    <VoteOne/>
                </div>
            </Mobile>
        </>
    )
}